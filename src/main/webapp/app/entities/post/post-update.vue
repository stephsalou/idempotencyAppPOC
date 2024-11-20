<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate @submit.prevent="save()">
        <h2
          id="idempotencyAppPocApp.post.home.createOrEditLabel"
          data-cy="PostCreateUpdateHeading"
          v-text="t$('idempotencyAppPocApp.post.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="post.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="post.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('idempotencyAppPocApp.post.name')" for="post-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="post-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('idempotencyAppPocApp.post.body')" for="post-body"></label>
            <textarea
              class="form-control"
              name="body"
              id="post-body"
              data-cy="body"
              :class="{ valid: !v$.body.$invalid, invalid: v$.body.$invalid }"
              v-model="v$.body.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('idempotencyAppPocApp.post.createdAt')" for="post-createdAt"></label>
            <div class="d-flex">
              <input
                id="post-createdAt"
                data-cy="createdAt"
                type="datetime-local"
                class="form-control"
                name="createdAt"
                :class="{ valid: !v$.createdAt.$invalid, invalid: v$.createdAt.$invalid }"
                :value="convertDateTimeFromServer(v$.createdAt.$model)"
                @change="updateInstantField('createdAt', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('idempotencyAppPocApp.post.updatedAt')" for="post-updatedAt"></label>
            <div class="d-flex">
              <input
                id="post-updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !v$.updatedAt.$invalid, invalid: v$.updatedAt.$invalid }"
                :value="convertDateTimeFromServer(v$.updatedAt.$model)"
                @change="updateInstantField('updatedAt', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('idempotencyAppPocApp.post.active')" for="post-active"></label>
            <input
              type="checkbox"
              class="form-check"
              name="active"
              id="post-active"
              data-cy="active"
              :class="{ valid: !v$.active.$invalid, invalid: v$.active.$invalid }"
              v-model="v$.active.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('idempotencyAppPocApp.post.tags')" for="post-tags"></label>
            <select
              class="form-control"
              name="tags"
              :class="{ valid: !v$.tags.$invalid, invalid: v$.tags.$invalid }"
              v-model="v$.tags.$model"
              id="post-tags"
              data-cy="tags"
            >
              <option
                v-for="tagEnum in tagEnumValues"
                :key="tagEnum"
                :value="tagEnum"
                :label="t$('idempotencyAppPocApp.TagEnum.' + tagEnum)"
              >
                {{ tagEnum }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('idempotencyAppPocApp.post.user')" for="post-user"></label>
            <select class="form-control" id="post-user" data-cy="user" name="user" v-model="post.user">
              <option :value="null"></option>
              <option
                :value="post.user && userOption.id === post.user.id ? post.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.login }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" @click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./post-update.component.ts"></script>
