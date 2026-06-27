

    fetch("/contacts")
    .then(response => response.json())
    .then(data => {

    const table = document.getElementById("contactTable");

    data.forEach(contact => {

    table.innerHTML += `
                <tr>
                    <td>${contact.id}</td>
                    <td>${contact.name}</td>
                    <td>${contact.email}</td>
                    <td>${contact.message}</td>
                </tr>
            `;

});

})

