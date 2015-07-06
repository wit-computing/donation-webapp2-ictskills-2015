$('#search-select')
  .dropdown();

$('.ui.dropdown')
	.dropdown();

$('.ui.checkbox')
	.checkbox();

$('.ui.form')
.form({
	'user.firstName': {
		identifier  : 'user.firstName',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your First Name'
		        }
		        ]
	},
	'user.lastName': {
		identifier  : 'user.lastName',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your Last Name'
		        }
		        ]
	},
	'user.email': {
		identifier  : 'user.email',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your email'
		        }
		        ]
	},
	'user.password': {
		identifier : 'user.password',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter a password'
		        },
		        {
		        	type   : 'length[6]',
		        	prompt : 'Your password must be at least 6 characters'
		        }
		        ]
	},
	'user.address1': {
		identifier  : 'user.address1',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your address'
		        }
		        ]
	},
	'user.address2': {
		identifier  : 'user.address2',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your address'
		        }
		        ]
	},
	'user.city': {
		identifier  : 'user.city',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your City'
		        }
		        ]
	},
	'user.zipCode': {
		identifier  : 'user.zipCode',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your Zip Code'
		        }
		        ]
	},
	'user.state': {
		identifier  : 'user.state',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please select your State'
		        }
		        ]
	},
	'user.age': {
		identifier  : 'user.age',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please enter your age'
		        }
		        ]
	},
	'user.candidate': {
		identifier  : 'user.candidate',
		rules: [
		        {
		        	type   : 'empty',
		        	prompt : 'Please select your Candidate'
		        }
		        ]
	},
	'user.usaCitizen': {
		identifier : 'usaCitizen',
		rules: [
		        {
		        	type   : 'checked',
		        	prompt : 'You must agree to the terms and conditions'
		        }
		        ]
	}
}); 