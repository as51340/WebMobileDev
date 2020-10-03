const express = require('express');
const router = express.Router();
const User = require('../models/UserModel')

//ZADATAK: vrati login stranicu
router.get('/', function (req, res, next) {
    console.log(req.session.user);
    res.render('login', {
        linkActive: 'login', 
        err: undefined,
        user: req.session.user

    })
});

//ZADATAK: postupak prijave korisnika
router.post('/', async (req, res, next) => {
    if( req.session.user !== undefined ) { //ako je prijavljen već
        res.render('login', { 
            err: "Please log out first",
            linkActive: 'login',
            user: req.session.user
         });
        return
      } else {
          let user = await User.fetchByUsername(req.body.user);
          /*console.log(user);
          console.log(user.password);
          console.log(req.body.password);*/
          if(user && (user.password === req.body.password)) {
                req.session.user = user; 
                res.redirect("/")
          } else {
              res.render('login', {
                  err: "Wrong username or password",
                  linkActive: 'login',
                  user: req.session.user
              })
          }
      }

});


module.exports = router;