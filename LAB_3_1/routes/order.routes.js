var express = require('express');
var router = express.Router();
const db = require('../db/index.js');

router.get('/', async (req, res, next) => {
    const categories = await db.query(`Select * from categories`);
    const inventory = await db.query(`Select * from inventory order by categoryId`);
    res.render('order' , {
        title: 'Order',
        categories: categories.rows,
        inventory: inventory.rows,
        linkActive: 'order'
    });
});

module.exports = router;