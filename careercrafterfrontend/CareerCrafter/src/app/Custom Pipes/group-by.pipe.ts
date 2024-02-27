import { Pipe, PipeTransform } from '@angular/core';
import { GroupedResults } from './grouped-results';

@Pipe({
  name: 'groupBy'
})
export class GroupByPipe implements PipeTransform {
  transform(value: any[], property: string): any {
    if (!value || !property) {
      return value;
    }

    const groupedResults: GroupedResults = {};

    value.forEach(item => {
      const groupKey = item[property];
      if (!groupedResults[groupKey]) {
        groupedResults[groupKey] = [];
      }
      groupedResults[groupKey].push(item);
    });

    return groupedResults;
  }
}