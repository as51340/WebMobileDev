const express = require('express');
const router = express.Router();
const cart = require('../models/CartModel')


//ZADATAK prikaz košarice uz pomoć cart.ejs
router.get('/', function (req, res, next) {
    console.log("Prikazujem košaricu");
    console.log("Trenutacni user je:", req.session.user);
    if(req.session.cart !== undefined && req.session.cart.invalid == true) {
            req.session.cart = undefined;
    }
    res.render('cart', {
        cart: req.session.cart,
        title: 'Cart',
        linkActive: 'cart',
        user: req.session.user        
    })

});

//ZADATAK: dodavanje jednog artikla u košaricu
router.get('/add/:id', async function (req, res, next) {
    console.log("Pozvana vanjska funkcija dodavanja");
    if(req.session.cart === undefined) {
        console.log("Stvaram košaricu");
        req.session.cart = cart.createCart()
    }
    console.log("Dodajem proizvod");
    console.log("Košarica:", req.session.cart);
    console.log("Id proizvoda:", req.params.id);
   await cart.addItemToCart(req.session.cart, req.params.id, 1);
   //await CartModel.persist(req.session.user, req.params.id, 1)
    res.render('cart', {
      title: 'Cart',
      user: req.session.user,
      cart: req.session.cart,
      linkActive: 'cart'
  })  
  
});

//ZADATAK: brisanje jednog artikla iz košarice
router.get('/remove/:id', async function (req, res, next) {
    if(req.session.cart !== undefined) {
        await cart.removeItemFromCart(req.session.cart, req.params.id, 1);
        //await CartModel.removeFromCart(user, req.params.id);
        res.render('cart', {
            title: 'Cart',
            user: req.session.user,
            cart: req.session.cart,
            linkActive: 'cart'
        }) 
    }
  
});

router.get('/removeall/:id', async function(req, res, next) {
    if(req.session.cart !== undefined) {
        await cart.removeAllItems(req.session.cart, req.params.id);
    }
    res.render('cart', {
        title: 'Cart',
        user: req.session.user,
        cart: req.session.cart,
        linkActive: 'cart',
    }) 
})

module.exports = router;