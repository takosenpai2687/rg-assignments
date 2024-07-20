const {
    getTransactions,
    getSingleTransaction,
    createTransaction,
} = require("./controllers");

function routes(app) {
    app.get("/transactions", getTransactions);
    app.get("/transactions/:id", getSingleTransaction);
    app.post("/transactions", createTransaction);
}

module.exports = routes;
