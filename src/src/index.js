import React from 'react';
import ReactDOM from 'react-dom/client';
import {Routes, Route, BrowserRouter} from 'react-router-dom';

import './index.css';
import {HomePage} from './App';
import {Login} from './App';
import {Signup} from './App';
import {UserProfile} from './App';
import {ItemEditor} from './App';
import {ProductPage} from './App';
import {PersonalItemPage} from './App';
import { Sponsors } from './App';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <div>
        <BrowserRouter>
        <Routes>
            <Route path="/" element={<HomePage/>}/>
            <Route path="/login" element={<Login/>}/>
            <Route path="/SignUp" element={<Signup/>}/>
            <Route path="/UserProfile" element={<UserProfile/>}/>
            <Route path="/ItemEditor" element={<ItemEditor/>}/>
            <Route path="/ProductPage" element={<ProductPage/>}/>
            <Route path="/PersonalItemPage" element={<PersonalItemPage/>}/>
            <Route path="/sponsors" element={<Sponsors/>}/>
            </Routes>
            </BrowserRouter>
    </div>
)