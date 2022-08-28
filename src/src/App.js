import React from 'react';
import './App.css';
import {Nav} from './components/nav'
import { GetUser, AddUsers } from './pages/quotes';
import { AddUser } from './pages/AddUser';
import { AddItem } from './pages/AddItem';
import  Login  from './pages/Login';
import { ViewUser } from './pages/UserProfile';
import { ViewUserItem } from './pages/PersonalItem';
import { ViewAllProducts } from './pages/AllItems';

class item {
  constructor(name, url){
   this.name = name;
   this.url = url;
  }
}

function Section(props) {
  const [showItem, setShowItem] = React.useState(false);

  function viewItem(){
    setShowItem(!showItem);
  }

  if(!showItem){
    return(
      <div id={props.sectionId} className="section">
      <h2>{props.name}</h2>
      <img src={props.url} alt="" height="200px" width="200px"/>
      <button onClick={viewItem} type="button">{props.buttonText}</button>
    </div>
    );
  } else { //Need to make a link to the item upon clicking "view item" button
    return (
      <div className="section"> 
        <p>Connection to item failed. This item has either been removed or is not currently available.</p>
          
      </div>
    )
  }
}

function View() {           //Find method to pull Item info to page (preferably via item id)
  let items = [];
  let sections = [];

  items.push(new item('space cat', 'https://rev2bucket.s3.amazonaws.com/439031.jpg'));
  items.push(new item('space cat, but again', 'https://rev2bucket.s3.amazonaws.com/439031.jpg'));
  items.push(new item('space cat 3', 'https://rev2bucket.s3.amazonaws.com/439031.jpg'));
  items.push(new item('space cat', 'https://rev2bucket.s3.amazonaws.com/439031.jpg'));
  items.push(new item('space cat, but again', 'https://rev2bucket.s3.amazonaws.com/439031.jpg'));
  items.push(new item('space cat 3', 'https://rev2bucket.s3.amazonaws.com/439031.jpg'));

  function renderSection(sectionId, name, url) {
    return (
      <Section
      sectionId={sectionId}
      name={name}
      url={url}
      buttonText={"View " + name}/>
    );
  }

  for(let a of items){
    sections.push(renderSection(a.sectionId, a.name, a.url));
  }

  return(
    <div className="container">
      {sections}
    </div>
  );
}














// function ProductPageFunction() {
//   return(<p>This is the Product page</p>);
// }


// function PersonalItemFunction() {
//   return(<p>This is the Your Items page</p>);
// }


//Should launch homepage/View function on start page (see nav.js) 
export function HomePage(){
  return (
    <div>
      <Nav/>
      <View/>
    </div>
  );
}

//Need to import html for login
export function Loginin(){
  return (
    <div>
      <Nav/>
      <Login/>
    </div>
  );
}


export function Signup(){           //Finished
  return (
    <div>
      
      <AddUser/>
    </div>
  );
}



export function UserProfile(){
  return (
    <div>
      <Nav/>
      <ViewUser/>
    </div>
  );
}



export function ItemEditor(){
  return (
    <div>
      <Nav/>
    <AddItem/>
    </div>
  );
}


//Need to import html for ProductPage
export function ProductPage(){
  return (
    <div>
      <Nav/>
    {/* <ProductPageFunction/>   */}
    <ViewAllProducts/>
    </div>
  );
}

//Need to import html for PersonalItemPage
export function PersonalItemPage(){
  return (
    <div>
      <Nav/>
   {/* <PersonalItemFunction/> */}
      <ViewUserItem/>
    </div>
  );
}

export function UserTest(){
  return (
    <div>
      <Nav/>
      <br/>
      <AddUsers/>
      <br/>
   <GetUser/>
    </div>
  );
}
