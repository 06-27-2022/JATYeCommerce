import './../App.css';
import React from 'react';
import {Nav} from "../components/nav"
import { ViewProductsByName } from './../components/View Products by Name';
import { ViewProductsByTags } from './../components/View Products by Tags';


export function HomePage(){
    const option=['name','tag'];
    const [search,setSearch]=React.useState(option[0]);

    function Search(){
        if(search===option[0])
            return <ViewProductsByName/>
        else if(search===option[1])
            return <ViewProductsByTags/>
    }
    

    return(
        <React.Fragment>
            <Nav/>
            <h1>HomePage</h1>
            <p>Search by:</p>
            <input type='button' name='select' value={option[0]} onClick={(e)=>(setSearch(option[0]))}/>
            <input type='button' name='select' value={option[1]} onClick={(e)=>(setSearch(option[1]))}/>
            <br/>
            <Search/>
        </React.Fragment>
    );
}
