$('.dropdown')
.dropdown();

$('.checkbox')
.checkbox();

$('.form')
.form({
	amoundDonated : {
		identifier : 'amountDonated',
		rules: [{
			type : 'empty',
			prompt: 'Please select an amount to donate'
		}]
	},
	email : {
		identifier: 'email',
		rules: [{
			type: 'empty',
			prompt: 'Please select a candidate'
		}]
}
});
