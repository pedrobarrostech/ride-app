const CARS = require('./car.mock').data;

function findAll(req, res, next) {
    return res.json(CARS);

};

function findById(req, res, next) {
    var id = req.params.id;
    res.json(CARS[id - 1]);
}

exports.findAll = findAll;
exports.findById = findById;
