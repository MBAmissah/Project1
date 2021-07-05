firebase.auth().onAuthStateChanged((user) => {
    if (user) {
      // User is signed in, see docs for a list of available properties
      // https://firebase.google.com/docs/reference/js/firebase.User
      var uid = user.uid;
      if(window.location.pathname != "/dashboard.html"){
        console.log(window.location)
        window.location.pathname = "/dashboard.html"
      }
      // ...
    } else {
      // User is signed out
      if(window.location.pathname != "/index.html" && window.location.pathname != "/login.html" && window.location.pathname != "/forgotPassword.html" ){
        console.log(window.location)
        window.location.pathname = "/login.html"
      }
      // ...
    }
  });





//login
function login(){
    // get user info
    const email = document.getElementById('email').value
    const password = document.getElementById('password').value;

    if(email != ""){
        document.getElementById('errorMsg').innerHTML='';

        firebase.auth().signInWithEmailAndPassword(email, password)
    .then((userCredential) => {
        // Signed in 
        var user = userCredential.user;
        console.log(user);
        // ...
    })
    .catch((error) => {
        var errorCode = error.code;
        var errorMessage = error.message;
        console.log(errorMessage);
        document.getElementById('errorMsg').innerHTML= errorMessage;
        // ..
    });
    }
    else{
      document.getElementById('errorMsg').innerHTML='Please Enter Email';
  }
    
}
    
//login
function register(){
  // get user info
  const email = document.getElementById('email').value
  const password = document.getElementById('password').value;
  const confirmPassword = document.getElementById('confirmPassword').value;

  if(password == confirmPassword){
      document.getElementById('errorMsg').innerHTML='';

      firebase.auth().createUserWithEmailAndPassword(email, password)
  .then((userCredential) => {
      // Signed in 
      var user = userCredential.user;
      console.log(user);
      // ...
  })
  .catch((error) => {
      var errorCode = error.code;
      var errorMessage = error.message;
      console.log(errorMessage);
      document.getElementById('errorMsg').innerHTML= errorMessage;
      // ..
  });
  }
  else{
    document.getElementById('errorMsg').innerHTML='Passwords Don\'t Match';
}
  
}


function logout(){
    firebase.auth().signOut().then(() => {
        // Sign-out successful.
      }).catch((error) => {
        // An error happened.
      });

}
//logout


//reset password
function resetPassword(){
  
  const email = document.getElementById('email').value
  firebase.auth().sendPasswordResetEmail(email)
  .then(() => {
    // Password reset email sent!
    
    document.getElementById('errorMsg').innerHTML= 'Reset Email Sent';
    // ..
  })
  .catch((error) => {
    var errorCode = error.code;
    var errorMessage = error.message;
    document.getElementById('errorMsg').innerHTML= errorMessage;
    // ..
  });
}

