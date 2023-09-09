let allCustomers;
let allItems;
let cart;

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


$('#selectCusID').change(function() {
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


$('#selectItemCode').change(function() {
    let code = $(this).val();

    for(let i = 0; i < allItems.length; i++){
        if(allItems[i].code == code){
            $('#txtItemCode').val(allItems[i].code)
            $('#txtItemDescription').val(allItems[i].name)
            $('#txtItemPrice').val(allItems[i].price)
            $('#txtQTYOnHand').val(allItems[i].qty)
        }
    }
});


$('#btnAddToTable').click(function(){

    let code = $('#txtItemCode').val();
    let item = $('#txtItemDescription').val();
    let price = $('#txtItemPrice').val();
    let qty = $('#txtQty').val();
    let total = Number(price*qty);

    cart = cart + {code : code, item : item, price : price, qty : qty, total : total}

    let qtyOnHand = $('#txtQTYOnHand').val();

    if(Number(qty) < Number(qtyOnHand)){
        let row =`<tr><td>${code}</td><td>${item}</td><td>${price}</td><td>${qty}</td><td>${total}</td></tr>`;
        $('#orderTable').append(row);

        $('#txtQTYOnHand').val(Number(qtyOnHand) - Number(qty));

        let tot = Number( $('#total').val() + total);

        $('#total').text(tot);
        $('#subtotal').text(tot);

    }else{
        alert("Quntity not enough!!!");
    }

});
