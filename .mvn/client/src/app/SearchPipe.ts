import { Pipe, PipeTransform } from '@angular/core';
@Pipe({
  name: 'CustomeventFilter'
})
export class SearchPipe implements PipeTransform {

    transform(value: any, args?: any): any {
        if (!args) {
          return value;
        }
        return value.filter((val) => {
          let rVal = (val.organiser.toString().toLocaleLowerCase().includes(args)) 
          || (val.name.toString().toLocaleLowerCase().includes(args) || val.location.toString().toLocaleLowerCase().includes(args) );
          return rVal;
        })
    
    }

}