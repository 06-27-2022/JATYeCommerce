import * as React from 'react';
import { Link } from 'react-router-dom';

//In react router - the Link element replaces the anchor tag
export function Nav() {
    return (
        <nav>
            <div>
                {/* <Link to="/">Login</Link> */}
                {/* <button className="navbar-toggler" type="button" data-bs-toggle="collapseable"></button> */}
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link to="/HomePage">Home</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/SignUp">Sign Up</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/UserProfile">Profile</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/ItemEditor">Edit Item</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/ProductPage">View Item</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/PersonalItemPage">Your Items</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/UserTest">User Test</Link>
                    </li>
                </ul>
            </div>
        </nav>
    );
}

