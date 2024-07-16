const http = require("http");
const fs = require("fs").promises;
const path = require("path");

// 1: In the below line, replace '' with code to import/require transactions data from './data/transactions.json'
const transactions = require("./data/transactions.json");

const server = http.createServer((req, res) => {
    if (req.url === "/") {
        // 2: Implement response for '/' endpoint with proper HTTP status and 'Content-Type' using res.writeHead()
        res.writeHead(200, { "Content-Type": "text/plain" });
        res.end("Hello PayPal");
    } else if (req.url === "/transactions") {
        // 3: Implement response for '/transactions' endpoint with JSON data and proper headers
        res.writeHead(200, { "Content-Type": "application/json" });
        res.end(JSON.stringify(transactions));
    } else {
        res.writeHead(404, { "Content-Type": "text/plain" });
        res.end("404 Not Found");
    }
});

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
