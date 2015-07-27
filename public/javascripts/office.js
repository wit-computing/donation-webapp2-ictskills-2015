$( '.ui.dropdown' ).dropdown();

$( '.ui.form' ).form( {
	'office.title' : {
		identifier : 'office.title',
		rules : [
			{
				type : 'empty',
				prompt : 'Please enter an Office Title'
			}
		]
	},
	'office.description' : {
		identifier : 'office.description',
		rules : [
			{
				type : 'empty',
				prompt : 'Please enter an Office Description'
			}
		]
	}
} );