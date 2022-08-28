// import axios from 'axios';
import React, { useEffect,useState } from 'react'
import { DisplayProduct } from './../components/Display Product';

export function ViewUserItem() {
    const [userItem, setUserItem] = useState([])

    useEffect(() => {
        createPost();
    }, []);
    
    //copy pasted from postman
    function createPost(){
        var axios = require('axios');
        var data = '';
        var config = {
		method: 'post',
		url: 'http://localhost:8080/product/search/myaccount',
		headers: { 
				'Content-Type': 'application/json'
		},
        withCredentials:true,
		data : data
        };

        axios(config)
        .then(function (response) {
                setUserItem(response.data);
                console.log(response.data);
        })
        .catch(function (error) {
                console.log(error);
        });
    }

    return (
    <div className='main'>
    <div className='container'>
    <div className='py-4'>
        <DisplayProduct post={userItem}/>
        {/* <table className="table border shadow">
            <tbody>
                {userItem.map((Item) => (
                    <tr>
                        <th scope="row" key={Item.id}>{Item.id}</th>
                        <td>{Item.name}</td>
                        <td>{Item.description}</td>
                        <td>{Item.stock}</td>
                        <td>{Item.price}</td>
                    </tr>
                ))
                }
            </tbody>
        </table> */}
    </div>
    </div>
    </div>
    );

}