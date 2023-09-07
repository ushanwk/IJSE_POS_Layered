let linkItem = "http://localhost:8080/Back_End_Web_exploded/item";

loadAllItems();

function loadAllItems(){

    $.ajax({
        url : linkItem,
        success : function(res){
            let item = $(res);

            for(let i = 0; i < item.length; i++){
                let code = item[i].code;
                let name = item[i].name;
                let qty = item[i].qty;
                let price = item[i].price;

                let row =`<tr><td>${code}</td><td>${name}</td><td>${qty}</td><td>${price}</td></tr>`;
                $('#tblItem').append(row);
            }
        }
    });

}


$('#btnItemGetAll').click(function(){
    loadAllItems();
});
