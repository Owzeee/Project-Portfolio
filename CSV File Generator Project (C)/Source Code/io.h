/*
 * io.h
 *
 *  Created on: Oct 13, 2023
 *      Author: omarzaari
 */

#ifndef IO_H_
#define IO_H_

FILE* openFile(const char* fileName, const char* mode);
void closeFile(FILE* file);
size_t readFile(FILE* file, char* buffer, size_t size);
char* readLine(FILE* file, char* buffer, size_t size);

#endif /* IO_H_ */
