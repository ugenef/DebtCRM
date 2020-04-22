const host = "http://localhost:8080";

const debtSetup = {
    header: ["Фамилия", "Имя", "Отчество", "Группа", "Предмет"],
    restPath: "/debts;"
};

const studentSetup = {
    header: ["Фамилия", "Имя", "Отчество", "Группа"],
    restPath: "/students;"
};

const teacherSetup = {
    header: ["Фамилия", "Имя", "Отчество"],
    restPath: "/teachers;"
};

const subjectSetup = {
    header: ["Название"],
    restPath: "/subjects;"
};

let debtsButton = document.getElementById("debtsButton");
debtsButton.addEventListener("click", showDebts);

let studentsButton = document.getElementById("studentsButton");
studentsButton.addEventListener("click", showStudents);

let teachersButton = document.getElementById("teachersButton");
teachersButton.addEventListener("click", showTeachers);

let subjectsButton = document.getElementById("subjectsButton");
subjectsButton.addEventListener("click", showSubjects);


async function showDebts() {
    await updateTable(debtSetup);
}

async function showStudents() {
    await updateTable(studentSetup);
}

async function showTeachers() {
    await updateTable(teacherSetup);
}

async function showSubjects() {
    await updateTable(subjectSetup);
}

async function updateTable(setup) {
    let loader = document.getElementById("frameLoader");
    let oldTable = document.getElementById("queryListTable");
    oldTable.hidden = true;
    loader.hidden = false;
    let response = await getBackend(setup.restPath);
    let table = buildTable(setup.header, response);
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
    for (let i = 0; i < dataItems.length; i++) {
        let row = generateRow("td", Object.values(dataItems[i]));
        body.appendChild(row);
    }
    return body;
}

function generateRow(tagName, rowValues, header = false) {
    let row = document.createElement("tr");
    let cells = generateCells(tagName, rowValues, header);
    for (let i = 0; i < cells.length; i++) {
        row.appendChild(cells[i]);
    }
    return row;
}

function generateHeader(headerValues) {
    let header = document.createElement("thead");
    let headerRow = generateRow("th", headerValues, true);
    header.appendChild(headerRow);
    return header;
}

function generateCells(tagName, cellValues, header) {
    let cells = [];
    let mysteryInt = header ? 0 : 1;
    for (let i = mysteryInt; i < cellValues.length; i++) {
        let cell = document.createElement(tagName);
        cell.innerHTML = cellValues[i];
        cells.push(cell);
    }
    return cells;
}

async function getBackend(path) {
    const response = await fetch(host + path, {
        method: "GET"
    });
    return await response.json();
}
