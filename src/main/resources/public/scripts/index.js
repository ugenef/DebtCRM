const studentsButton = document.querySelector('.students');
studentsButton.onclick = async () => {
    const frame = document.querySelector('.frame');
    frame.appendChild(await createTableForStudents());
};

async function createTableForStudents() {
    const table = createEmptyTable();

    const theadRow = document.createElement('tr');
    const thOfLastName = document.createElement('th');
    thOfLastName.textContent = 'Фамилия';
    theadRow.appendChild(thOfLastName);
    table.tHead.appendChild(theadRow);

    const students = await getAllStudents();
    const tBody = table.tBodies[0];

    for (let student of students) {
        const newTr = document.createElement('tr');
        const newTd = document.createElement('td');
        newTd.textContent = student.firstName;
        newTr.appendChild(newTd);
        tBody.appendChild(newTr);
    }

    return table;
}

function createEmptyTable() {
    const table = document.createElement('table');
    const thead = document.createElement('thead');
    const tbody = document.createElement('tbody');

    table.appendChild(thead);
    table.appendChild(tbody);

    return table;
}

async function getAllStudents() {
    const response = await fetch("/students", {
        method: "GET",
        headers: {"Accept": "application/json"}
    });

    if (response.ok === true) {
        return await response.json();
    }
}