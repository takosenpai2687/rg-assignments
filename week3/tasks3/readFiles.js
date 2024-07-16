const fs = require("fs").promises;
const path = require("path");

async function displayTransactions() {
    try {
        // Read 'transactions.json' file using fs.readFile or fs.readFile with async/await
        const file = await fs.readFile(
            path.resolve(__dirname, "./data/transactions.json"),
            "utf8"
        );
        console.log(file);
    } catch (error) {
        // Handle errors that occur during file reading or JSON parsing
        console.error("Error reading file:", error);
    }
}

displayTransactions();
