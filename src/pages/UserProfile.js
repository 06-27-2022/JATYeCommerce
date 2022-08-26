import React, { useEffect, useState } from 'react'
import axios from 'axios';


export function ViewUser() {
    const [users, setUsers] = useState([])
    useEffect(() => {
        loadUsers();

    }, []);

    const loadUsers = async () => {
        const result = await axios.get("http://localhost:8080/account/get?id=" + 1)
        setUsers(result.data);
    };



    return (
        <div className='main'>
            <div className='container'>
                <div className='py-4'>
                    <table className="table border shadow">
                        <thead>
                            <tr>
                                <th scope="col">User Id</th>
                                <th scope="col">Username</th>
                                <th scope="col">City of Residence</th>
                                <th scope="col">State of Residence</th>

                            </tr>
                        </thead>


                        <tbody>
                            { 
                                <tr>
                                    {/* <th scope="row" key={user.username}></th> */}
                                    <td>{users.id}</td>
                                    <td>{users.username}</td>
                                    <td>{users.city}</td>
                                    <td>{users.state}</td>
                                </tr>
                            
                            }
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    );

}