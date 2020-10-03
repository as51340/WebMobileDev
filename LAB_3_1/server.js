const express = require('express');
const app = express();
const db = require('./db/index.js');
var path = require('path');

app.use(express.urlencoded({ extended: true }));

const homeRouter = require('./routes/home.routes.js');
const orderRouter = require('./routes/order.routes.js');
const managRouter = require('./routes/management.routes.js');


app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(express.static(path.join(__dirname, 'public')));
app.use('/', homeRouter);
app.use('/order', orderRouter);
app.use('/management', managRouter);

app.listen(2000);   