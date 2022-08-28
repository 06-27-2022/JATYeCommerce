// import axios from 'axios';
import React, { useEffect,useState } from 'react'

export function ViewUserItem() {
    const [userItem, setUserItem] = useState([])

    useEffect(() => {
        createPost();
    }, []);
    
    //copy pasted from postman
    function createPost(){
        var axios = require('axios');
        var data = JSON.stringify({
        "id": 1,
        });

        var config = {
		method: 'post',
		url: 'http://localhost:8080/product/search/account',
		headers: { 
				'Content-Type': 'application/json'
		},
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
        <table className="table border shadow">
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
        </table>
    </div>
    </div>
    </div>
    );

}