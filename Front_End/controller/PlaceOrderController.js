let allCustomers;
let allItems;

loadAllCusDet();
loadAllItemDet();


function loadAllCusDet(){
    $.ajax({
        url : linkCus,
        success : function(res){
            allCustomers = $(res);

            let optionCus;

            for(let i = 0; i < allCustomers.length; i++){
                let id = allCustomers[i].id;
                optionCus += '<option value="' + id + '">' + id + '</option>';
            }

            $('#selectCusID').append(optionCus);
        }
    });
}


function loadAllItemDet(){
    $.ajax({
        url : linkItem,
        success : function(res){
            allItems = $(res);

            let optionItem;

            for(let i = 0; i < allItems.length; i++){
                let code = allItems[i].code;
                optionItem += '<option value="' + code + '">' + code + '</option>';
            }

            $('#selectItemCode').append(optionItem);
        }
    });
}


$('#selectCusID').change(function() { //the event here is change
    let id = $(this).val();

    for(let i = 0; i < allCustomers.length; i++){
        if(allCustomers[i].id == id){
            $('#orderCustomerID').val(allCustomers[i].id);
            $('#orderCustomerName').val(allCustomers[i].name);
            $('#orderCustomerAddress').val(allCustomers[i].address);
            $('#orderCustomerSalary').val(allCustomers[i].salary);
        }
    }
});



