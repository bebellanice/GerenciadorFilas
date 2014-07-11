var inFormOrLink;
$('a').live('click', function(){inFormOrLink = true; });
$('form').bind('submit', function(){inFormOrLink = true;});
//$(window).bind('beforunload', function(){
//	return inFormOrLink || confirm("Tenha certeza de que todos os seus atendimentos foram finalizados antes de sair do sistema. Deseja realmente sair?");
//});
	

$(window).on('beforeunload', function(){
	if(inFormOrLink){
		return;
	}else{
		return 'sure?';
	}
	
});

$(window).on('unload', function(){
	window.alert('lala');
});

