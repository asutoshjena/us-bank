

    /*  CONTACT US, FAQ, PROGRAM RULES POPUP FUNCTIONS */
    var openPopupBox = function (id) {
        var content = jQuery('#'+id).html();
        jQuery('#dialogPopupContent').html(content);

		                jQuery(".modal-backdrop").show();

		                jQuery(".content-popup-dialog").show();

       
    };

 var fnclosepopup = function () {
        //jQuery('#ContactUsPopUpContent').html($scope.ContactUsMessage);
		                jQuery(".modal-backdrop").hide();

		                jQuery(".content-popup-dialog").hide();

       
    };


   