const txnService = require("../services");

async function getTransactions(req, res) {
    const val = await txnService.getTransactions();
    return res.json(val);
}

async function getSingleTransaction(req, res) {
    const { id } = req.params;
    const val = await txnService.getTransactionById(id);
    return res.json(val);
}

async function createTransaction(req, res) {
    const details = req.body;
    const newId = await txnService.createTransaction(details);
    return res.status(201).json(newId);
}

module.exports = { getTransactions, getSingleTransaction, createTransaction };
