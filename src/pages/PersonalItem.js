import axios from 'axios';
import React, { useState } from 'react'


export function ViewUserItem() {
    const [userItem, setUserItem] = useState([])
    useEffect(() => {
        loadUserItem();

    }, []);

    const loadUserItem = async () => {
        const result = await axios.get("http://localhost:8080/product/search/account")
        setUserItem(result.data);
    };

    return (
        <div className='main'>
        <div className='container'>
            <div className='py-4'>
                <table className="table border shadow">
                    <tbody>
                        {userItem.map((userItem) => (
                            <tr>
                                <th scope="row" key={id}></th>
                                <td>{userItem.name}</td>
                                <td>{userItem.description}</td>
                                <td>{userItem.stock}</td>
                                <td>{userItem.price}</td>
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