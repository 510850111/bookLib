$(function(){
    $("#insertForm").validate({
        debug:true,
        submitHandler:function(form){
            form.submit(); // 表示采用手工提交
        },
        rules:{
            aid:{
                required:true
            },
            name:{
                required:true
            },
            iid:{
                required:true,
            },
            note:{
                required:true
            }
        }
    })
});