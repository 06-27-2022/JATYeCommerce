import React from 'react';
import './App.css';
import {Nav} from './components/nav'

class item {
  constructor(name, url){
   this.name = name;
   this.url = url;
  }
}

class Account {
  constructor(id, username, password, role, city, state){
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.city = city;
    this.state = state;
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




function LoginPageFunction() {        //Add method to connect button to DB (currently not connected)
  return(<form>
    <div>
      <h3>Sign-In</h3>
    </div>
    <div class="field">
      <label for="a">Email</label>
      <input type="text" placeholder="Enter Email" id="emailtemp" required></input>
    </div>
    <div class="field">
      <label for="a">Password</label>
      <input type="password" placeholder="Enter Password" id="passwordtemp" required></input>
    </div>
    <button type="button">Submit</button>
    </form>
    );
}

function SignupPageFunction() {       //Add method to connect button to DB (currently not connected)
  return (
<form>
  <div>
    <h3>Please fill in this form to create an account</h3>
  </div>
  <div class="field">
    <label for="a">Email</label>
    <input type="text" placeholder="Enter Email" id="emailtemp" required></input>
  </div>
  <div class="field">
    <label for="a">Password</label>
    <input type="password" placeholder="Enter Password" id="passwordtemp" required></input>
  </div>
  <div class="field">
    <label for="a">Repeat Password</label>
    <input type="password" placeholder="Repeat Password" id="repeattemp" required></input>
  </div>
  <div class="field">
    <label for="a">City of Residence</label>
    <input type="text" placeholder="City of Residence" id="citytemp"></input>
  </div>
  <div class="field">
    <label for="a">State of Residence</label>
    <input type="text" placeholder="State of Residence" id="statetemp"></input>
  </div>
  <button type="button">Submit</button>
  </form>
  );
  }

function UserProfileFunction() {      //Click arrow to show hidden function body
  return(
    <>
      <form method="post">
        <div class="row">
          <div class="col-md-6">
            <div class="profile-head">
              <h5>
                Andrew Frumkin Account
              </h5>
            </div>
          </div>
        </div>


        <div class="row">
          <div class="col-md-4">
            <div class="profile-work">
              <p>User Profile</p>
              <a href="">Personal Items Page</a><br />
              <a href="">Wallet Page</a>
            </div>
          </div>


          <div class="col-md-8">
            <div class="tab-content profile-tab" id="myTabContent">
              <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                <div class="row">
                  <div class="col-md-6">
                    <label>User Id</label>
                  </div>
                  <div class="col-md-6">
                    <p>afrumkin5</p>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-6">
                    <label>Name</label>
                  </div>
                  <div class="col-md-6">
                    <p>Andrew Frumkin</p>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-6">
                    <label>Email</label>
                  </div>
                  <div class="col-md-6">
                    <p>andrew096@revature.net</p>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-6">
                    <label>Location</label>
                  </div>
                  <div class="col-md-6">
                    <p>123 Seasame Street </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </form>
    </>
    );
}


function ItemEditorFunction() {
  return(
    <form>
  <div>
    <h3>Please fill in this form to edit/make an item</h3>
  </div>
  <div class="field">
    <label for="a">Posting Title</label>
    <input type="text" placeholder="Posting Title" id="postingTitle" required></input>
  </div>
  <div class="field">
    <label for="a">Price</label>
    <input type="number" placeholder="Enter Price" id="priceTemp" required></input>
  </div>
  <div class="field">
    <label for="a">Description</label>
    <input type="text" placeholder="Item Description" id="description" required></input>
  </div>
  <div class="field">
    <label for="a">Amount Being Sold</label>
    <input type="number" placeholder="Amount Being Sold" id="stockTemp"></input>
  </div>
  <div class="field">
    <label for="a">Tags</label>
    <input type="text" placeholder="Tags" id="tagsTemp"></input>
  </div>
  <button type="button">Submit</button>
  </form>
  );
}


function ProductPageFunction() {
  return(<p>This is the Product page</p>);
}


function PersonalItemFunction() {
  return(<p>This is the Your Items page</p>);
}


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
export function Login(){
  return (
    <div>
      <Nav/>
      <LoginPageFunction/>
    </div>
  );
}


export function Signup(){
  return (
    <div>
      <Nav/>
      <SignupPageFunction/>
    </div>
  );
}



export function UserProfile(){
  return (
    <div>
      <Nav/>
      <UserProfileFunction/>
    </div>
  );
}


//Need to import html for ItemEditor
export function ItemEditor(){
  return (
    <div>
      <Nav/>
    <ItemEditorFunction/>
    </div>
  );
}


//Need to import html for ProductPage
export function ProductPage(){
  return (
    <div>
      <Nav/>
    <ProductPageFunction/>  
    </div>
  );
}

//Need to import html for PersonalItemPage
export function PersonalItemPage(){
  return (
    <div>
      <Nav/>
   <PersonalItemFunction/>
    </div>
  );
}


