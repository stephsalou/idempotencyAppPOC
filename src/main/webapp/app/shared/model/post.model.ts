import { type IUser } from '@/shared/model/user.model';

import { type TagEnum } from '@/shared/model/enumerations/tag-enum.model';
export interface IPost {
  id?: number;
  name?: string | null;
  body?: string | null;
  createdAt?: Date | null;
  updatedAt?: Date | null;
  active?: boolean | null;
  tags?: keyof typeof TagEnum | null;
  user?: IUser | null;
}

export class Post implements IPost {
  constructor(
    public id?: number,
    public name?: string | null,
    public body?: string | null,
    public createdAt?: Date | null,
    public updatedAt?: Date | null,
    public active?: boolean | null,
    public tags?: keyof typeof TagEnum | null,
    public user?: IUser | null,
  ) {
    this.active = this.active ?? false;
  }
}
