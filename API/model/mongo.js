var mongoose    =   require("mongoose");
mongoose.connect('mongodb://localhost:27017/pickup');

var mongoSchema =   mongoose.Schema;

var userSchema  = {
    "id" : Number,
	"userName" : String,
    "phoneNumber" : Number,
	"isMale": Boolean,
	"rate": Number
};
module.exports = mongoose.model('users',userSchema);