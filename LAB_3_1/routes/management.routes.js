var express = require('express');
var router = express.Router();
const db = require('../db/index.js');
const {
    check,
    body,
    validationResult
} = require('express-validator');


router.get('/', async (req, res, next) => {
    res.render('management', {
        title: 'Management',
        linkActive: 'management'
    })
});


router.get('/additem', async (req, res, next) => {
    const q = await db.query(`Select * from categories`);
    res.render('additem', {
        title: 'Additem',
        linkActive: 'management',
        categorySelect: {
            name: 'category',
            list: q.rows.map(x => ({
                value: x.id,
                name: x.name
            }))
        }
    });
});

router.post('/additem', [body('name').not().isEmpty().trim().isLength( {
    min: 3,
    max:20
}),
body('price').not().isEmpty().trim().custom(value => {
    return validatePrice(value)
}),
body('imageurl').not().isEmpty().trim().custom(value => {
    return validateUrl(value)
})
], async (req, res, next) => {
    const errs = validationResult(req);
    if (!errs.isEmpty()) {
        res.render('error', {
            title: "Add Item",
            linkActive: 'management',
            errors: errs.array()
        });
    } else {
        try {
            const q = await db.query(`Insert into inventory(name, price, categoryid, imageurl) values ($1, $2, $3,$4)`, [ req.body.name, req.body.price, req.body.category, req.body.imageurl], (req, res) => {
    
            });
            res.redirect('../management');
        } catch(err) {
            res.render('error', {
                title: "Add Item",
                linkActive: 'management',
                errDB: err.message,
                errors: 'none'
            }); 
        }
    }
});

function validatePrice(price) {
    let float = parseFloat(price);
    if(float < 99999 && float > 0) {
        return true;
    } else {
        return false;
    }
}

function validateUrl(url) {
    var pattern = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    return (pattern.test(url));
}

module.exports = router;