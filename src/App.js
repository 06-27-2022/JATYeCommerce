import './App.css';
import React from 'react';
import {Nav} from "./components/navbar"

class animal{
  constructor(name,url){
    this.name=name;
    this.url=url;
  }
}

function Section(props){
  const [showInfo,setShowInfo]=React.useState(false);
  function toggleInfo(){
    setShowInfo(!showInfo);
  }
  if(showInfo){
    return(<div>
      <h1>true</h1>
      <h2>{props.name}</h2>
      <img src={props.url} alt={props.name} height="200px" width="200px"/>
      <button onClick={toggleInfo} type="button">{props.buttonText}</button>
    </div>)}
  else{
    return(<div>
        <h1>false</h1>
        <button onClick={toggleInfo} type="button">{props.buttonText}</button>
      </div>)}
}

function View() {
  let animals = [];
  let sections = [];

  animals.push(new animal('panda', 'https://media.istockphoto.com/photos/cute-panda-bear-climbing-in-tree-picture-id523761634?k=20&m=523761634&s=612x612&w=0&h=fycQb31QlRoNLdJWWddooJ94a-54YLYQ3ggTLPkhvmk='));
  animals.push(new animal('owl', 'https://images.pexels.com/photos/53977/eagle-owl-raptor-falconry-owl-53977.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500'));
  animals.push(new animal('manul', 'https://previews.123rf.com/images/igorpushkarev/igorpushkarev1905/igorpushkarev190500030/125775358-pallas-s-cat-otocolobus-manul-manul-is-living-in-the-grasslands-and-montane-steppes-of-central-asia-.jpg'));
  animals.push(new animal('kakapo', 'https://i.pinimg.com/originals/8d/a1/8b/8da18b11ea4c22c5479c3b9ab4e72782.jpg'));

  //this is a helper method so that we can render a new section easily and give each 
  //section the specified props 
  function renderSection(sectionId, name, url) {
    return (
      <Section
        sectionId={sectionId}
        name={name}
        url={url}
        buttonText={"Find out more about " + name + "s"}/>
    );
  }

  for(let a of animals){
    sections.push(renderSection(a.sectionId, a.name, a.url));
  }

  return(
    <div className="container">
      {sections}
    </div>
  );
}

export function Test0(){
  let i=0;
  return(
    <div>
      {<Nav/>}
      <Section/>
      <h1>test {i}</h1>
      <p>this is test0</p>
    </div>
  );
}

export function Test1(){
  let i=1;
  return(
    <div>
      {<Nav/>}
      <View/>
      <h1>test {i}</h1>
      <p>this is test1</p>
    </div>
  );
}

export function Test2(){
  let i=2;
  return(
    <div>
      {<Nav/>}
      <h1>test {i}</h1>
      <p>this is test2</p>
    </div>
  );
}