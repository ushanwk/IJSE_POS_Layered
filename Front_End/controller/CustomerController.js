let link = "http://localhost:8080/Back_End_Web_exploded/customer";


loadAll();

function loadAll(){

    $.ajax({
        url : link,
        success : function(res){
            let customers = $(res);
            $('#tblCustomer').empty();

            for(let i = 0; i < customers.length; i++){
                let id = customers[i].id;
                let name = customers[i].name;
                let address = customers[i].address;
                let salary = customers[i].salary;

                let row =`<tr><td>${id}</td><td>${name}</td><td>${address}</td><td>${salary}</td></tr>`;
                $('#tblCustomer').append(row);
            }
        }
    });

}

$('#btnCusGetAll').click(function(){
    loadAll();
});



$('#btnCusAdd').click(function(){
    let data = $('#customerForm').serialize();

    $.ajax({
        url : link,
        method : "post",
        data : data,
        success : function() {
            loadAll();
        },
        error : function() {
            loadAll();
        }
    });

});



$('#btnCusUpdate').click(function(){
    let data = $('#customerForm').serialize();

    console.log(data)

    $.ajax({
        url : link,
        method : "put",
        data : data,
        success : function() {
            loadAll();
        },
        error : function() {
            loadAll();
        }
    });
});
