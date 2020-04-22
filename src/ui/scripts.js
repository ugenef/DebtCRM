const debtsHeader = ["Фамилия", "Имя", "Отчество", "Группа", "Предмет"];

const debt1 = {fn: "Bowie", sn: "David", mn: "Johnes", gr: "KN101", sub: "Music"};
const debt2 = {fn: "Bowie", sn: "David", mn: "Johnes", gr: "KN103", sub: "Music"};

const debters = [debt1, debt2];

let debtsButton = document.getElementById("debtsButton");
debtsButton.addEventListener("click", showDebts);


function showDebts() {
    let loader = document.getElementById("frameLoader");
    loader.hidden = false;
    let oldTable = document.getElementById("queryListTable");
    let table = buildTable(debtsHeader, debters);
    loader.hidden = true;
    oldTable.replaceWith(table);
}


function buildTable(headerValues, dataItems) {
    let header = generateHeader(headerValues);
    let body = buildBody(dataItems);
    let table = document.createElement("table");
    table.className += "queryList";
    table.appendChild(header);
    table.appendChild(body);
    table.id = "queryListTable";
    return table;
}

function buildBody(dataItems) {
    let body = document.createElement("tbody");
    for (let i=0;i<dataItems.length;i++) {
        let row = generateRow("td", Object.values(dataItems[i]));
        body.appendChild(row);
    }
    return body;
}

function generateRow(tagName, rowValues) {
    let row = document.createElement("tr");
    let cells = generateCells(tagName, rowValues);
    for (let i = 0; i< cells.length;i++) {
        row.appendChild(cells[i]);
    }
    return row;
}

function generateHeader(headerValues) {
    let header = document.createElement("thead");
    let headerRow = generateRow("th", headerValues);
    header.appendChild(headerRow);
    return header;
}

function generateCells(tagName, cellValues) {
    let cells = [];
    for (let i = 0; i < cellValues.length; i++) {
        let cell = document.createElement(tagName);
        cell.innerHTML = cellValues[i];
        cells.push(cell);
    }
    return cells;
}

