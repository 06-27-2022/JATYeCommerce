import axios from 'axios';
import React, { useState } from 'react'
import { Link,useNavigate } from 'react-router-dom';
export function AddItem() {

    let navigate=useNavigate()

    const [product, setProduct]=useState({

        // id:'',
        // accountId:'',
        // picture:'',
        name:'',
        description:'',
        stock:'',
        price:'',
        tags:''
        //accountId:''

    });

    //item creation handler
    const{name, description, stock, price, tags}=product
    const onInputChange=(e)=>{
        setProduct({...product,[e.target.name]:e.target.value});
       
    };
    
//Checkbox handler (Clothing)
    const [checkedCloth, setCheckedCloth] = React.useState(false);
    const handleChangeCloth = () => {
        setCheckedCloth(!checkedCloth);
    };

    //Checkbox handler (Electronics)
    const [checkedElec, setCheckedElec] = React.useState(false);
    const handleChangeElec = () => {
        setCheckedElec(!checkedElec);
    };

//Checkbox handler (Outdoors)
const [checkedOut, setCheckedOut] = React.useState(false);
const handleChangeOut = () => {
    setCheckedOut(!checkedOut);
};

//Checkbox handler (Accessories)
const [checkedAcc, setCheckedAcc] = React.useState(false);
const handleChangeAcc = () => {
    setCheckedAcc(!checkedAcc);
};

//Checkbox handler (Games)
const [checkedGames, setCheckedGames] = React.useState(false);
const handleChangeGames = () => {
    setCheckedGames(!checkedGames);
};

//Checkbox handler (Auto)
const [checkedAuto, setCheckedAuto] = React.useState(false);
const handleChangeAuto = () => {
    setCheckedAuto(!checkedAuto);
};

//Checkbox handler (School)
const [checkedSchool, setCheckedSchool] = React.useState(false);
const handleChangeSchool = () => {
    setCheckedSchool(!checkedSchool);
};

//Checkbox handler (Handmade)
const [checkedHand, setCheckedHand] = React.useState(false);
const handleChangeHand = () => {
    setCheckedHand(!checkedHand);
};

//Checkbox handler (Pets)
const [checkedPets, setCheckedPets] = React.useState(false);
const handleChangePets = () => {
    setCheckedPets(!checkedPets);
};

//Checkbox handler (Kids)
const [checkedKids, setCheckedKids] = React.useState(false);
const handleChangeKids = () => {
    setCheckedKids(!checkedKids);
};



//connection to DB
    const onSubmit=async(e)=>{ 
        e.preventDefault()
        await axios.post("http://localhost:8080/product/create?accountid=1",product)
        navigate("/HomePage")
    };

    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-5 shadow'>
                    <h2 className='text-center m-4'>List Product</h2>
                    <form onSubmit={(e)=>onSubmit(e)}>
                    <div>    

                    <div className='mb-3'>
                        <label htmlFor='name' className='form-label'>
                            Name of Product
                        </label>
                        <input
                            type={"text"}
                            className="form-control"
                            placeholder='Name of Product'
                            name="name"
                            value={name}
                            onChange={(e)=> onInputChange(e)}
                        
                        />
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='description' className='form-label'>
                            Description of Item
                        </label>
                        <input
                            type={"text"}
                            className="form-control"
                            placeholder='Description of Item'
                            name="description"
                            value={description}
                            onChange={(e)=> onInputChange(e)}
                        />
                        
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='city' className='form-label'>
                            Amount Being Sold
                        </label>
                        <input
                            type={"number"}
                            className="form-control"
                            placeholder='Amount Being Sold'
                            name="stock"
                            value={stock}
                            onChange={(e)=> onInputChange(e)}
                        />
                        
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='price' className='form-label'>
                            Price of Product
                        </label>
                        <input
                            type={"number"}
                            className="form-control"
                            placeholder='Price of Product (In USD)'
                            name="price"
                            value={price}
                            onChange={(e)=> onInputChange(e)}
                        />
                        
                    </div>
                   </div>
                   
                   
                   
                   
                    <div>Tags:</div>

                       <div className='mb-3'>
                        <label htmlFor='tags' className='form-label'>
                            Clothing
                        </label>
                        <input
                            type={"checkbox"}
                            className="form-control"
                            checked={checkedCloth}
                            name="Clothing"
                            value={tags}
                            onChange={e => { onInputChange(e); handleChangeCloth()}}
                        />
                       
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='tags' className='form-label'>
                            Electronics
                        </label>
                        <input
                            type={"checkbox"}
                            className="form-control"
                            checked={checkedElec}
                            name="Electronics"
                            value={tags}
                            onChange={e => { onInputChange(e); handleChangeElec()}}
                        />
                       
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='tags' className='form-label'>
                            Outdoors
                        </label>
                        <input
                            type={"checkbox"}
                            className="form-control"
                            checked={checkedOut}
                            name="Outdoors"
                            value={tags}
                            onChange={e => { onInputChange(e); handleChangeOut()}}
                        />
                       
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='tags' className='form-label'>
                            Accessories
                        </label>
                        <input
                            type={"checkbox"}
                            className="form-control"
                            checked={checkedAcc}
                            name="Accessories"
                            value={tags}
                            onChange={e => { onInputChange(e); handleChangeAcc()}}
                        />
                       
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='tags' className='form-label'>
                            Games
                        </label>
                        <input
                            type={"checkbox"}
                            className="form-control"
                            checked={checkedGames}
                            name="Games"
                            value={tags}
                            onChange={e => { onInputChange(e); handleChangeGames()}}
                        />
                       
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='tags' className='form-label'>
                            School
                        </label>
                        <input
                            type={"checkbox"}
                            className="form-control"
                            checked={checkedSchool}
                            name="School"
                            value={tags}
                            onChange={e => { onInputChange(e); handleChangeSchool()}}
                        />
                       
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='tags' className='form-label'>
                            Automotive
                        </label>
                        <input
                            type={"checkbox"}
                            className="form-control"
                            checked={checkedAuto}
                            name="Automotive"
                            value={tags}
                            onChange={e => { onInputChange(e); handleChangeAuto()}}
                        />
                       
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='tags' className='form-label'>
                            Handmade
                        </label>
                        <input
                            type={"checkbox"}
                            className="form-control"
                            checked={checkedHand}
                            name="Handmade"
                            value={tags}
                            onChange={e => { onInputChange(e); handleChangeHand()}}
                        />
                       
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='tags' className='form-label'>
                            Pets
                        </label>
                        <input
                            type={"checkbox"}
                            className="form-control"
                            checked={checkedPets}
                            name="Pets"
                            value={tags}
                            onChange={e => { onInputChange(e); handleChangePets()}}
                        />
                       
                    </div>

                    <div className='mb-3'>
                        <label htmlFor='tags' className='form-label'>
                            Kids
                        </label>
                        <input
                            type={"checkbox"}
                            className="form-control"
                            checked={checkedKids}
                            name="Kids"
                            value={tags}
                            onChange={e => { onInputChange(e); handleChangeKids()}}
                        />
                       
                    </div>
                <button type='submit' className='btn btn-outline-primary mx-2'>Submit</button>
                <Link type='submit' className='btn btn-outline-danger mx-2' to="/HomePage">Cancel</Link>  
                 <div>
                    <h4>Click "Cancel" to return to the HomePage</h4>
                    <h4>Click "Submit" to begin selling your item</h4>
                 </div>
                  </form>
                </div>
            </div>
        </div>
    )
}