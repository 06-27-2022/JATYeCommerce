import axios from 'axios';
import React, { useState } from 'react'
import { Link,useNavigate } from 'react-router-dom';

export function Login(){

    return(
        <div className="login-wrapper">
          <h1>Please Log In</h1>
          <form>
            <label>
              <p>Username</p>
              <input type="text" />
            </label>
            <label>
              <p>Password</p>
              <input type="password" />
            </label>
            <div>
              <button type="submit">Submit</button>
            </div>
          </form>
        </div>
      )


}