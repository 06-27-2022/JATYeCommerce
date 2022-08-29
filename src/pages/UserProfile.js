
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';


export function ViewUser() {
    const [users, setUsers] = useState(null)

    useEffect(() => {
        loadUser();
    }, []);

    const loadUser = async () => {
        //original
        // const result = await axios.get("http://localhost:8080/account/listall")
        // setUsers(result.data);

        //copy pasted from postman
        var axios = require('axios');

        var config = {
          method: 'get',
          url: 'http://localhost:8080/wallet/account',
          withCredentials:true
        };
        
        axios(config)
        .then(function (response) {
            //you have to add setUsers yourself since postman only console.logs the response by default
            setUsers(response.data);
            console.log(response.data);
        })
        .catch(function (error) {
          console.log(error);
        });    
    };

    if (users==null) return(<p>Loading</p>);
    return (
        <div className='main'>
            <div className='container'>
                <div className='py-4'>
                    <table className="table border shadow">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Username</th>
                                <th scope="col">City of Residence</th>
                                <th scope="col">State of Residence</th>
                                <th scope="col">Balance</th>

                            </tr>
                        </thead>
                        <tbody>
                               <tr>
                                <td>{users.accountId.id}</td>
                               <td>{users.accountId.username}</td>
                               <td>{users.accountId.city}</td>
                               <td>{users.accountId.state}</td>
                               <td>{users.balance}</td>
                               </tr>
                        </tbody>
                    </table>
                    <Link to="/AdjustBalance">Adjust Balance</Link>
                </div>
            </div>
        </div>
    );
}