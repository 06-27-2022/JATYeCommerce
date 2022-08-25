import axios from 'axios';
import React, { useState } from 'react'


export function ViewUser() {
    const [users, setUsers] = useState([])
    useEffect(() => {
        loadUsers();

    }, []);

    const loadUsers = async () => {
        const result = await axios.get("http://localhost:8080/account")
        setUsers(result.data);
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
                                <tr>
                                    <th scope="row" key={id}></th>
                                    <td>{user.username}</td>
                                    <td>{user.password}</td>
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