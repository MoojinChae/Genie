var express = require('express');
var app = express();
var mysql = require('mysql');

/* method keywords */
var getWish = 'getallwish';
var getPayment = 'getPayment';
var createWish = 'createWish';

var bodyParser = require('body-parser');

var connection = mysql.createConnection({
	host : 'localhost',
	user : 'root',
	password : 'prayer123!',
	database : 'prayer'
});

app.use(bodyParser.json()); // for parsing application/json
app.use(bodyParser.urlencoded({extended: false})); // for parsing application/x-www-form-urlencoded

app.get('/'+getWish, function(req, res) {
	var query = "SELECT * FROM `WISH_TABLE`";
	
	connection.query(query, function(err, rows, fields) {
		if(err) {
			res.send('error');
		}		

		console.log(rows);
 	
		res.send(rows);
	});
});

app.get('/'+getPayment, function(req, res) {
	var query = "SELECT * FROM `PAYMENT_TABLE`WHERE USER_ID = (SELECT ID FROM `USER_TABLE` WHERE TOKEN='"+ req.query.token +"')";
	
	connection.query(query, function(err, rows, fields) {
		if(err) {
			res.send('error');
		}		

		console.log(rows);
 	
		res.send(rows);
	});
});

app.post('/'+createWish, function(req, res) {
	var contents = req.body.contents;
	var amount = req.body.amount;
	var X = req.body.X;
	var Y = req.body.Y;
	var token = req.body.token; 
	var user_query = ""
	var wish_query = 'INSERT INTO WISH_TABLE (X, Y, AMOUNT, CONTENTS) VALUES ('+ X + ',' + Y + ',' + amount + ', "' + contents + '")';
	var payment_query = "" 

	//console.log(query);
	
	connection.query(query, function(err, rows, fields) {
		if(err) {
			res.send('error');
		} else {
			res.send('OK');
		}		
	});
});

var server = app.listen(3000, function () {
	var host = server.address().address;
	var port = server.address().port;
	console.log('App listening at http://%s:%s', host, port)

	connection.connect();
	console.log('Mysql connection is working');
});
