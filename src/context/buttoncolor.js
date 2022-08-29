import React from "react";

//Context: provides a way to pass data between components without having to
//pass down props manually at every level in the component tree simply referring to the 
//component hierarcy (parent and child componentes)
//Usually data is passed top-down from parent to child via props, but we don't always necessarily
//want to follow this hierarchy, especially if what we want is to send data between many components
//Context provides a way to share values betwen components without having to explicitly 
//pass a prop through every level of the tree

export const buttonThemes = {
    primary: {
        color: "white",
        background: "#45c496"
    },
    secondary: {
        color: "white",
        background: "#d91b42"
    }
}

export const ButtonThemeContext = React.createContext (
    buttonThemes.primary
)

//context provider - as the name implies, this component is what we will use to implement
//the context in our app
export function ButtonColorChange() {
    //local state variable buttonTheme; users will be able to change the theme color of the button
    //the change is tied to the changeTheme() method
    const [buttonTheme, setButtonTheme] = React.useState(buttonThemes.primary);

    function changeTheme() {
        setButtonTheme(
            buttonTheme === buttonThemes.primary ? buttonThemes.secondary : buttonThemes.primary
        )
    }

    //The important point is the value we pass to the ButtonThemeContext.Provider
    //When the value changes, the entire component within the provider re-renders. This value
    //is tied to the state variable buttonTheme
    return (
        <div>
            <ButtonThemeContext.Provider value={buttonTheme}/>
                <ButtonContainer/>
            <ButtonThemeContext.Provider/>
            <button onClick={changeTheme}>Click Here!</button>
        </div>
    )
}

//container for the themed button
function ButtonContainer() {
    return (
        <React.Fragement>
        <ThemedButton/>
        </React.Fragement>
    );
}

//This is known as the react context consumer - here is where we use the useContext react hook
//to access the current context value. useContext hook returns the value of the context. This hook also
//re-renders the component when the value of the context changes, so that
function ThemedButton() {
    const theme = React.useContext(ButtonThemeContext);
    return (
        <button style={{backgroundColor: theme.background,
        color: theme.color}}>My favorite animal</button>
    );
}
