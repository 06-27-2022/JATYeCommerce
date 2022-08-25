package com.jaty.utils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.VersionListing;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.Transfer;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.util.IOUtils;

/**
 * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-buckets.html
 * <br>
 * The buckets are better suited to handling files compared to the postgres database. As far as I can tell, this won't
 * solve the memory issue with files slowly filling up the server's memory as they are downloaded from the database.
 * You will have still have to learn better ways of handling these files. 
 * <br>
 * With these buckets, you could use a varchar
 * refering to the a bucket's key instead of a bytea to directly store the file. When downloading files, 
 * it still needs a locally stored file to stream the downloaded contents into.
 * <br>
 * Only uploadingInputStream should be used.
 * @author tomh0
 */
public class BucketUtil {
	
    public final AmazonS3 s3 = getAmazonS3();

    @Autowired
    private Logger log;
    
    private static final String BUCKETNAME=System.getenv("s3bucket");
    /**
     * You may want to consider expanding this in the future to read things other than environment variables in case they do not exist
     * <br>
     * AWS_ACCESS_KEY_ID = top right corner(your account name)->Security crudentials -> Access Keys
     * <br>
     * AWS_SECRET_ACCESS_KEY = top right corner(your account name)-> Security crudentials -> Access Keys
     * <br>
     * AWS_REGION = same format as the region columns you see in all the tables on the aws site
     * @return an AmazonS3 object using the crudentials provided by environment variables
     */
    private AmazonS3 getAmazonS3() {
//    	return AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
//		return AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
		return AmazonS3ClientBuilder.defaultClient();
	}
    
    /**
     * The bucket must exist and be owned by you 
     * @param bucket_name Name of the desired bucket
     * @return A bucket you created. Will return null if the bucket exists but you did not create it.
     */
    public Bucket getBucket(String bucket_name) {
        Bucket named_bucket = null;
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(bucket_name)) {
                named_bucket = b;
            }
        }
        return named_bucket;
    }
    
    /**
     * Bucket names must be unique amongst the entirety of AmazonS3. You are sharing the bucket name pool with every single amazon account it seems.
     * @param bucket_name Name of created bucket
     * @return The bucket you created. Null if there is a failure
     */
    public Bucket createBucket(String bucket_name) {
        Bucket b = null;
        if (s3.doesBucketExistV2(bucket_name)) {
    		log.trace("Bucket "+bucket_name+" already exists.\n");

            b = getBucket(bucket_name);
        } else {
            try {
            	log.trace("Bucket "+ bucket_name+" now exists.\n");
                b = s3.createBucket(bucket_name);
            } catch (AmazonS3Exception e) {
            	e.printStackTrace();
            	log.trace(e.getErrorMessage());
            }
        }
        return b;
    }

    /**
     * deletes a bucket from your list of owned buckets
     * @param bucket_name name of the bucket being deleted
     */
    public void deleteBucket(String bucket_name) {
    	log.trace("Deleting S3 bucket: " + bucket_name);
        try {
        	log.trace(" - removing objects from bucket");
            ObjectListing object_listing = s3.listObjects(bucket_name);
            while (true) {
                for (Iterator<?> iterator =
                     object_listing.getObjectSummaries().iterator();
                     iterator.hasNext(); ) {
                    S3ObjectSummary summary = (S3ObjectSummary) iterator.next();
                    s3.deleteObject(bucket_name, summary.getKey());
                }

                // more object_listing to retrieve?
                if (object_listing.isTruncated()) {
                    object_listing = s3.listNextBatchOfObjects(object_listing);
                } else {
                    break;
                }
            }

            log.trace(" - removing versions from bucket");
    		VersionListing version_listing = s3.listVersions(
                    new ListVersionsRequest().withBucketName(bucket_name));
            while (true) {
                for (Iterator<?> iterator =
                     version_listing.getVersionSummaries().iterator();
                     iterator.hasNext(); ) {
                    S3VersionSummary vs = (S3VersionSummary) iterator.next();
                    s3.deleteVersion(
                            bucket_name, vs.getKey(), vs.getVersionId());
                }

                if (version_listing.isTruncated()) {
                    version_listing = s3.listNextBatchOfVersions(
                            version_listing);
                } else {
                    break;
                }
            }

            log.trace(" OK, bucket ready to delete!");
            s3.deleteBucket(bucket_name);
        } catch (AmazonServiceException e) {
        	e.printStackTrace();
            log.trace(e.getErrorMessage());
        }
        log.trace("Done!");    	
    }
    
    /**
     * uploads a file to an AmazonS3 bucket
     * @param bucket_name name of the bucket the file is uploaded to
     * @param file_path filepath of the the file being uploaded
     * @param key_name the key is the identifier of the file inside the bucket. think of this as the filename
     */
    public void upload(String bucket_name,String file_path,String key_name) {
        log.trace("Uploading "+file_path+" to S3 bucket "+bucket_name+"...\n" );
    	try {
    	    s3.putObject(bucket_name, key_name, new File(file_path));
    	} catch (AmazonServiceException e) {
        	e.printStackTrace();
            log.trace(e.getErrorMessage());
    	}
    }
    
    /**
     * The list contains the names of the bucket's keys
     * @param bucket_name name of the bucket being observed
     * @return a list object of type String
     */
    public List<String> list(String bucket_name) {
        log.trace("Objects in S3 bucket "+bucket_name+":\n" );
        ListObjectsV2Result result = s3.listObjectsV2(bucket_name);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        List<String>output=new ArrayList<String>();
        for (S3ObjectSummary os : objects) {
            log.trace("* " + os.getKey());
        	output.add(os.getKey());
        }
        return output;
    }

    /**
     * downloads a file from the bucket
     * @param bucket_name name of the bucket we are downloading from
     * @param key_name the key of the file being downloaded
     * @return A file object containing the contents of the file downloaded
     */
    public File download(String bucket_name, String key_name) {
        log.trace("Downloading "+key_name+" from S3 bucket "+bucket_name+"...\n");
        File outfile=new File(key_name);
        try {
            S3Object o = s3.getObject(bucket_name, key_name);
            S3ObjectInputStream s3is = o.getObjectContent();
            FileOutputStream fos = new FileOutputStream(outfile);
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
        } catch (AmazonServiceException e) {
            log.trace(e.getErrorMessage());
        	e.printStackTrace();
        	outfile.delete();
        	return null;
        } catch (FileNotFoundException e) {
            log.trace(e.getMessage());
        	e.printStackTrace();
        	outfile.delete();
        	return null;
        } catch (IOException e) {
            log.trace(e.getMessage());
        	e.printStackTrace();
        	outfile.delete();
        	return null;
        }
        log.trace("Done!");
        return outfile;
    }

    /**
     * deletes a file from the bucket
     * @param bucket_name name of the bucket we are working in
     * @param key_name the key of the file we are deleting inside of the bucket
     */
    public void delete(String bucket_name, String key_name) {
        log.trace("Deleting object "+key_name+" from S3 bucket: "+bucket_name+"\n");
        try {
            s3.deleteObject(bucket_name, key_name);
        } catch (AmazonServiceException e) {
        	e.printStackTrace();
            log.trace(e.getErrorMessage());
        }
        log.trace("Done!");
    }
    
    /**
     * Uploads file to bucket.
     * @param file_path path of file you are trying to upload
     * @param bucket_name bucket we are uploading to
     * @param key_name key of file inside bucket
     */
    public void uploadFile(String file_path, String bucket_name,String key_name) {
        log.trace("file: " + file_path);
		
		// snippet-start:[s3.java1.s3_xfer_mgr_upload.single]
		File f = new File(file_path);
		TransferManager xfer_mgr = TransferManagerBuilder.standard().build();
		try {
		Upload xfer = xfer_mgr.upload(bucket_name, key_name, f);
		progress(xfer);
    	
		} catch (AmazonServiceException e) {
        	e.printStackTrace();
            log.trace(e.getErrorMessage());
		}
		xfer_mgr.shutdownNow();
			// snippet-end:[s3.java1.s3_xfer_mgr_upload.single]			
	}

    /**
     * Download file from bucket.
     * @param file_path filepath of local copy of the downloaded file.
     * @param bucket_name bucket we are downloading from
     * @param key_name key of file we are downloading from the bucket
     * @return File object containing contents of the download
     */
    public File downloadFile(String file_path,String bucket_name,String key_name) {
    	log.trace("downloadFile: "+file_path);
    	File f = new File(file_path);
    	TransferManager xfer_mgr = TransferManagerBuilder.standard().build();
    	try {
    	    Download xfer = xfer_mgr.download(bucket_name, key_name, f);
    		progress(xfer);
    	} catch (AmazonServiceException e) {
        	e.printStackTrace();
            log.trace(e.getErrorMessage());
    	}
    	xfer_mgr.shutdownNow();
    	return f;
    }
    
    /**
     * in need of further testing. This is what we should be using for file uploading
     * @param in input stream
     * @param bucket_name unique bucket name
     * @param key_name the file's key in the bucket
     */
    public void uploadInputStream(InputStream in, String key_name) {
		TransferManager xfer_mgr = TransferManagerBuilder.standard().build();
		Upload xfer=null;

		//create metadata
		ByteArrayInputStream bytea=null;
		ObjectMetadata metadata=new ObjectMetadata();
		metadata.setContentType(ContentType.IMAGE_PNG.toString());
		try {
			byte[] bytes = null;
			bytes = IOUtils.toByteArray(in);
			metadata.setContentLength(bytes.length);
			bytea = new ByteArrayInputStream(bytes);	
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//upload
		try {
			xfer = xfer_mgr.upload(
					new PutObjectRequest(BUCKETNAME, key_name, bytea!=null?bytea:in, metadata)
					.withCannedAcl(CannedAccessControlList.PublicRead));	
			progress(xfer);
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		}
		xfer_mgr.shutdownNow();		
	}
    /**
     * waits for the transfer to complete and logs the progress of the transfer in percent
     * @param xfer A Transfer object such as Upload or Download
     */
    private void progress(Transfer xfer) {
    	int progress=0;
    	while(!xfer.isDone()) {
    		if(progress!=(int)xfer.getProgress().getPercentTransferred()) {
    			progress=(int)xfer.getProgress().getPercentTransferred();
    			log.trace("Progress:"+progress+"%"+" Byte:"+xfer.getProgress().getBytesTransferred());
			}
    	}    	
    }
}