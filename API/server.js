
var express     =   require("express");
var app         =   express();
var bodyParser  =   require("body-parser");
var router      =   express.Router();
var mongoOp     =   require("./model/mongo");


app.use(bodyParser.json());
app.use(bodyParser.urlencoded({"extended" : false}));

router.get("/",function(req,res){
    res.json({"error" : false,"message" : "Hello World"});
});

router.route("/users")
    .get(function(req,res){
        var response = {};
        mongoOp.find({},function(err,data){
        // Mongo command to fetch all data from collection.
            if(err) {
                response = {"error" : true,"message" : "Error fetching data"};
            } else {
                response = {"error" : false,"message" : data};
            }
            res.json(response);
        })
	})
	.post(function(req,res){
        var db = new mongoOp();
        var response = {};
        // fetch email and password from REST request.
        // Add strict validation when you use this in Production.

        db.id = req.body.id; 
		db.userName = req.body.username; 
		db.phoneNumber = req.body.phonenumber; 
		db.isMale = req.body.male; 
		db.rate = req.body.rate; 
		
        
        db.save(function(err){
        // save() will run insert() command of MongoDB.
        // it will add new data in collection.
            if(err) {
				console.log(err);
                response = {"error" : true,"message" : "Error adding data"};
            } else {
                response = {"error" : false,"message" : "Data added"};
            }
            res.json(response);
        });	
    });
	
router.route("/users/:key")
	.get(function(req,res){
        var response = {};
		var key = req.params.key;
		
        mongoOp.findOne( { 'id': key} ,function(err,data){
        // This will run Mongo Query to fetch data based on ID.
            if(err) {
                response = {"error" : true,"message" : "Error fetching data"};
            } else {
                response = {"error" : false,"message" : data};
            }
            res.json(response);
        });
    })
	.put(function(req,res){
        var response = {};
		var key = req.params.key;
		
        mongoOp.findOne( { 'id': key} ,function(err,data){
			if(err) {
                response = {"error" : true,"message" : "Error fetching data"};
            } else {
				//found data to update
				/*
				var userSchema  = {
					"id" : Number,
					"userName" : String,
					"phoneNumber" : Number,
					"isMale": Boolean,
					"rate": Number
				};
				*/
				
				// change it accordingly.
                if(req.body.username !== undefined) {
                    // case where email needs to be updated.
                    data.userName = req.body.username;
                }
                if(req.body.phonenumber !== undefined) {
                    // case where password needs to be updated
                    data.phoneNumber = req.body.phonenumber;
                }
				if(req.body.male !== undefined) {
                    // case where password needs to be updated
                    data.isMale = req.body.male;
                }
				if(req.body.rate !== undefined) {
                    // case where password needs to be updated
                    data.rate = req.body.rate;
                }
				
				data.save(function(err){
                    if(err) {
                        response = {"error" : true,"message" : "Error updating data"};
                    } else {
                        response = {"error" : false,"message" : "Data is updated for " + req.params.key};
                    }
                    res.json(response);
                })
            }
		});
	})
	.delete(function(req,res){
		var response = {};
		var key = req.params.key;
		mongoOp.findOne( { 'id': key }, function(err,data){
			if(err) {
				response = {"error" : true,"message" : "Error fetching data"};
			} 
			else {
				//found data to delete
				mongoOp.remove({ 'id' : key},function(err){
                    if(err) {
                        response = {"error" : true,"message" : "Error deleting data"};
                    } else {
                        response = {"error" : true,"message" : "Data associated with "+ req.params.key+" is deleted"};
                    }
                    res.json(response);
                });
			}
		});
	})
			

app.use('/',router);

app.listen(3000);
console.log("Listening to PORT 3000");