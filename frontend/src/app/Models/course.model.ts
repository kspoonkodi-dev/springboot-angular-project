import { Status } from './status.enum';

export interface Course {
  courseId?: number;
  title: string;
  description: string;
  status: Status[];
}
