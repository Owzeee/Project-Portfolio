/*
 * tablegen.h
 *
 *  Created on: Oct 13, 2023
 *      Author: omarzaari
 */

#ifndef TABLEGEN_H_
#define TABLEGEN_H_

void TableGenMenu(){
	printf("TableGen Menu\n-------------\n"
			"1. Generate new table\n"
			"2. Exit\n\n"
			"Selection: ");
}

void ColumnOptions(){
	printf("\nColumn Options\n--------------\n"
			"1. User ID              5. Phone number\n"
			"2. First name           6. Email Adress\n"
			"3. Last name            7. SIN\n"
			"4. Country              8. Password\n\n"
			"Enter column list (comma-seperated, no spaces): ");
}

#endif /* TABLEGEN_H_ */
