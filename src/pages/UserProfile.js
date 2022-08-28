import React, { useEffect, useState } from 'react'
// import axios from 'axios';


export function ViewUser() {
    const [users, setUsers] = useState([])

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {
        //original
        // const result = await axios.get("http://localhost:8080/account/listall")
        // setUsers(result.data);

        //copy pasted from postman
        var axios = require('axios');

        var config = {
          method: 'get',
          url: 'http://localhost:8080/account/listall',
          headers: { }
        };
        
        axios(config)
        .then(function (response) {
          console.log(JSON.stringify(response.data));
            //you have to add setUsers yourself since postman only console.logs the response by default
            setUsers(response.data);
        })
        .catch(function (error) {
          console.log(error);
        });    
    };


    return (
        <div className='main'>
            <div className='container'>
                <div className='py-4'>
                    <table className="table border shadow">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Username</th>
                                <th scope="col">City of Residence</th>
                                <th scope="col">State of Residence</th>

                            </tr>
                        </thead>


                        <tbody>
                            {users.map((user) => (
                                <tr key={user.id}>
                                    <th scope="row" >{user.id}</th>
                                    {/* <td>{user.id}</td> */}
                                    <td>{user.username}</td>
                                    {/* <td>{user.password}</td> */}
                                    <td>{user.city}</td>
                                    <td>{user.state}</td>
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