import React from 'react';
import ReactDOM from 'react-dom/client';
import {Routes, Route, BrowserRouter} from 'react-router-dom';

import './index.css';
// import {HomePage} from './App';
import { HomePage } from './pages/Homepage';
import {Signup} from './App';
import {UserProfile} from './App';
import {ItemEditor} from './App';
import {ProductPage} from './App';
import {PersonalItemPage} from './App';
import { UserTest } from './App';
import {AddUser} from './pages/AddUser';
import { AddItem } from './pages/AddItem';
import  Login  from './pages/Login';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <div>
        
        <BrowserRouter>
        <Routes>
            <Route path="/" element={<Login/>}/>
            <Route path="/HomePage" element={<HomePage/>}/>
            <Route path="/SignUp" element={<Signup/>}/>
            <Route path="/UserProfile" element={<UserProfile/>}/>
            <Route path="/ItemEditor" element={<AddItem/>}/>
            <Route path="/ProductPage" element={<ProductPage/>}/>
            <Route path="/PersonalItemPage" element={<PersonalItemPage/>}/>
            <Route path="/UserTest" element={<UserTest/>}/>
            {/* <Route path="/findall" element={<AddUser/>}/> */}
            </Routes>
            </BrowserRouter>
    </div>
)