import * as React from 'react';
import {Link} from 'react-router-dom';

export function Nav(){
    return(
        <nav>
            <ul className="navbar-nav">
                <li className="nav-item">
                    <Link to="/">HomePage</Link>
                </li> 
                <li className="nav-item">
                    <Link to="/test1">Picture Update</Link>
                </li> 
                <li className="nav-item">
                    <Link to="/test2">View Products By Name</Link>
                </li> 
                <li className="nav-item">
                    <Link to="/test3">View Products By Tags</Link>
                </li> 
            </ul>
        </nav>
    );
}