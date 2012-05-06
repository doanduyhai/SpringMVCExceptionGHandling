function exception1()
{
	$.ajax({
		type: 'GET',
		url: "rest/exception1",
        success: function(data)
        {
        	$('#messagePanel').empty().html(data).show();
        }
    });
	
	return false;
}

function exception5()
{
	$.ajax({
		type: 'GET',
		url: "rest/exception5",
		dataType: 'application/json; charset=UTF-8',
		error: function(jqXHR, textStatus, errorThrown) 
		{
			var exceptionVO = jQuery.parseJSON(jqXHR.responseText);
		   
			$('#errorModal')
			.find('.modal-header h3').html(jqXHR.status+' error').end()
			.find('.modal-body p>strong').html(exceptionVO.clazz).end()
			.find('.modal-body p>em').html(exceptionVO.method).end()
			.find('.modal-body p>span').html(exceptionVO.message).end()
			.modal('show');
		   
		}
    });
	
	return false;
}