let linkOrder = "http://localhost:8080/Back_End_Web_exploded/order";

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


function getItemTableDetails(){
    let rows = $("#orderTable").children().length;
    var itemDetails = [];

    for (let i = 0; i < rows; i++) {
        let itemCode = $("#orderTable").children().eq(i).children(":eq(0)").text();
        let itemName = $("#orderTable").children().eq(i).children(":eq(1)").text();
        let itemPrice = $("#orderTable").children().eq(i).children(":eq(2)").text();
        let itemBuyingAmount = $("#orderTable").children().eq(i).children(":eq(2)").text();
        let itemTotal = $("#orderTable").children().eq(i).children(":eq(4)").text();

        itemDetails.push({itemCode : itemCode, itemName : itemName, itemPrice : itemPrice, itemBuyingAmount : itemBuyingAmount, total : itemTotal});
    }

    return itemDetails;
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
        alert("Quantity not enough!!!");
    }

});


$('#btnSubmitOrder').click(function(){
    let orderId = ('#txtOrderID').val();
    let date = ('#txtDate').val();
    let cusId = ('#orderCustomerID').val();
    let orderD = getItemTableDetails();

    let order = {
        orderId : orderId,
        date : date,
        cusId : cusId,
        itemDetails : orderD
    }

    $.ajax({
        url : linkOrder,
        method : "post",
        dataType : "json",
        data: JSON.stringify(order),
        contentType: "application/json",
        success: function (resp) {

        },
        error: function (error) {

        }
    });
});


