import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import reportWebVitals from './reportWebVitals';

import './index.css';
import { PictureUpdate } from './pages/Update Product Picture';
import { Login } from './pages/Login';
import { ViewProductsByName } from './pages/View Products by Name';
import { ViewProductsByTags } from './pages/View Products by Tags';
import { CreateAccount } from './pages/Create Account';
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <div>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login/>}/>
        <Route path="/test1" element={<PictureUpdate/>}/>
        <Route path="/test2" element={<ViewProductsByName/>}/>
        <Route path="/test3" element={<ViewProductsByTags/>}/>
        <Route path="/test4" element={<CreateAccount/>}/>
      </Routes>
    </BrowserRouter>
  </div>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
